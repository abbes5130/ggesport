<?php

namespace App\Controller;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use App\Entity\Comments;
use App\Entity\Likes;

use Knp\Component\Pager\PaginatorInterface ;
use App\Entity\News;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\SerializerInterface;
use JMS\Serializer\SerializerBuilder as SerializerBuilder;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
 use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FraneworkExtraBundle\Configuration\Hothed;
use Synfony\Conponent\Validator\Constraints\Json;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class CommentsssController extends AbstractController
{
       /**
    * @Route("/addNewsJson", name="add_NewsJson")
    *Method("POST")
    */
    public function addNewsJson(Request $request,NormalizerInterface $Normalizer)
    {
        $News = new News();
        $News->setTitle($request->query->get('title'));
  
        $News->setDescription($request->query->get('description'));
        $News->setCreationDate(new \DateTime('now'));
        
       

        $News->setImg($request->query->get('bg_img'));
        $News->setBgImg($request->query->get('img'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($News);
        $em->flush();
     
        $jsonContent = $Normalizer->normalize($News, 'json', ['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));    }
 
    

    /**
    * @Route("/addcommentsJson/{id}", name="add_commentsJson",methods={"POST"})
    */
    public function addCommentJson(Request $request,NormalizerInterface $Normalizer,$id)
    {
        $post = $this->getDoctrine()
        ->getRepository(News::class)
        ->findPostByid($id);

        $comment = new Comments();
        //$container=$request->request->get('comment');

        $comment->setNews($post);
        $comment->setText($request->query->get('text'));
        $comment->setCommentDate(new \DateTime('now'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($comment);
        $em->flush();
        $jsonContent = $Normalizer->normalize($comment, 'json', ['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));    }
      /**
    * @Route("/listnewsJson", name="list_newsJson",methods={"GET"})
    */
    public function AllJsonNews(NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(News::class);
        $comments = $repository->findAll();

        $jsonContent = $Normalizer->normalize($comments, 'json', ['groups'=>'post:read']);



        return new Response(json_encode($jsonContent));
    }
    /** 
    * @Route("/api/NewsJson/{id}/view", name="NewsJSON" ,methods={"GET"})
*/
public function NewsIdJson(Request $request ,$id ,NormalizerInterface $Normalizer){

$em = $this->getDoctrine()->getManager();
$News = $em->getRepository(News::class)->find($id);

$jsonContent = $Normalizer->normalize($News, 'json', ['groups'=>'post:read']);

return new Response(json_encode($jsonContent));
}
  /** 
    * @Route("/api/CommentsJson/{id}/view", name="NewsJSON" ,methods={"GET"})
*/
public function CommentsIdJson(Request $request ,$id ,NormalizerInterface $Normalizer){

    $em = $this->getDoctrine()->getManager();
    $Comment = $em->getRepository(Comments::class)->find($id);
    
    $jsonContent = $Normalizer->normalize($Comment, 'json', ['groups'=>'post:read']);
    
    return new Response(json_encode($jsonContent));
    }
 /**
    * @Route("/listcommentJson", name="list_commentsJson")
    *Method("GET")
    */
    public function AllCommentsJson(NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Comments::class);
        $comments = $repository->findAll();

        $jsonContent = $Normalizer->normalize($comments, 'json', ['groups'=>'post:read']);



        return new Response(json_encode($jsonContent));
    }
      /**
    * @Route("/api/deleteNewsJson/{id}/view", methods={"DELETE"})
    */
    public function deletenewsJson(Request $request,NormalizerInterface $Normalizer)
    {
        $id = $request->get('id');
        $em= $this->getDoctrine()->getManager();
        $news=$em->getRepository(News::class)->find($id);
        $em->remove($news);
        $em->flush();
        $jsonContent = $Normalizer->normalize($news, 'json', ['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));    }
          /**
       * @Route("api/updateJson/{id}" , name="updateactJson", methods={"PUT"})
       
       */
    public function updatenewsJson(Request $request,NormalizerInterface $Normalizer)
    {
            $em=$this->getDoctrine()->getManager();
            $News=$this->getDoctrine()->getManager()
                         ->getRepository(News::class)
                         ->find($request->get("id"));
             $News->setTitle ($request->get("title"));
            $News->setDescription($request->get("description"));
            $News->setCreationDate(new \DateTime('now'));
            $News->setBgImg($request->get("bg_img"));
            $News->setImg($request->get("img"));


            $em->persist($News);
            $em->flush();
            $jsonContent = $Normalizer->normalize($News, 'json', ['groups'=>'post:read']);
            return new Response(json_encode($jsonContent)); 
    }
     /**
     * @Route("/addlikeJson")
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     */
    public function likeJson(Request $request,NormalizerInterface $Normalizer)
    {

        $comment = $this->getDoctrine()
        ->getRepository(Comments::class)
        ->findPostByid($request->query->get('comments_id'));
       // $comment = $this->getDoctrine()->getRepository(Comments::class)->find(Request::createFromGlobals()->request->get('id'));

        
       //if (!$this->getDoctrine()->getRepository(Likes::class)->findOneBy(['Comments' => $comment->getId()])) {
            $like = new Likes();
            $like->setComments($comment);

            $em = $this->getDoctrine()->getManager();
            $em->persist($like);
            $em->flush();

            $jsonContent = $Normalizer->normalize($like, 'json', ['groups'=>'post:read']);
            return new Response(json_encode($jsonContent)); 
       // }
    }
      /**
     * @Route("/apiJSon/News/{id}", methods={"GET"})
     */
    public function JsonJsonlistAction(Request $request,NormalizerInterface $Normalizer, PaginatorInterface $paginator, int $id)
    {

        $query = $this->getDoctrine()->getRepository(Comments::class)->findCommentsByArticleQuery($id);
$s=$this->getDoctrine()->getRepository(Comments::class)->findAll();
        $jsonContent = $Normalizer->normalize($query, 'json', ['groups'=>'post:read','comment']);
        return new Response(json_encode($jsonContent)); 
    }
}
